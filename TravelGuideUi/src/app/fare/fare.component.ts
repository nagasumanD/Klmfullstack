import { environment } from "../../environments/environment.prod";
import { ILogRequest } from "../models/LogRequest";
import { IFares } from "../models/Fares";
import { ServicesService } from "../services.service";
import { Component, OnInit } from "@angular/core";
import { HttpErrorResponse, HttpParams } from "@angular/common/http";

@Component({
  selector: "app-fare",
  templateUrl: "./fare.component.html",
  styleUrls: ["./fare.component.css"],
})
export class FareComponent implements OnInit {
  originInput: any;
  destinationInput: any;
  airportsList: any;
  closeairports: boolean;
  origin: any;
  destination: any;
  log: ILogRequest;
  orginSelected: any;
  destinationSelected: any;
  spinner: boolean;
  currenyRadio: string;
  languageRadio: string;
  sbtDisable: boolean;
  fare: number;
  showFare: boolean ;
  fareCurrency: string;
  currentPage: number;
  pageLength: number;
  term: string;
  pageSize: string;

  constructor(private service: ServicesService) {
    this.pageLength = 12;
    this.currenyRadio='USD';
    this.languageRadio='en';
    this.sbtDisable=true;
    this.showFare=false;
    this.pageSize='12';
    this.closeairports=true;

  }

  ngOnInit(): void {
    let parms = new HttpParams()
      .set("size", this.pageSize)
      .set("lang", this.languageRadio);
    this.getAirportsList(parms);
  }

  filterAirports(e) {
    this.term = e.target.value;

    if (this.term) {
      let parms = new HttpParams()
        .set("size", this.pageSize)
        .set("lang", this.languageRadio)
        .set("term", this.term);
      this.getAirportsList(parms);
    }
  }
  getAirportsList(parms: any) {
    this.log = { ...this.log, requestType: environment.airportsList };
    let present = performance.now();
    this.service.getAirPorts(parms).subscribe(
      (data) => {
        this.airportsList = data._embedded.locations.sort((a, b) =>
          a.name.localeCompare(b.name)
        );

        this.currentPage = data.page.number;
        this.pageLength = data.page.totalPages;
        console.log(this.pageLength);
        if (this.orginSelected) {
          let orginindex = this.airportsList.find(
            (airport) => airport.code === origin
          );
          this.airportsList.splice(orginindex, 1);
        }
        this.log = {
          responseCode: 200,
          requestType: environment.airportsList,
          responseTime: performance.now() - present,
        };
        this.service
          .logginResponseandrequest(this.log)
          .subscribe((data) => console.log(data));
      },
      (error: HttpErrorResponse) => {
        this.airportsList = undefined;
        this.pageLength = 0;
        this.log = {
          responseCode: error.status,
          requestType: environment.airportsList,
          responseTime: performance.now() - present,
        };
        this.service
          .logginResponseandrequest(this.log)
          .subscribe((data) => console.log(`saved response to the server`));
        console.log(`failed saved response to the server`);
        console.error(error);
      }
    );
  }

  trackByAriportId(index, airport) {
    return airport.code;
  }

  selectedAirport(e) {
    if (this.orginSelected) {
      console.log(`orgintion has values`);
      this.destinationSelected = e;
      this.sbtDisable = false;
    } else {
      console.log(`orifing filled`);
      this.orginSelected = e;
      if (this.destinationSelected) {
        console.log(`origin is filled and has distintion`);
        this.sbtDisable = false;
      }
    }
  }
  onDeleteDestination(e) {
    this.destinationSelected = undefined;
    this.sbtDisable = true;
  }
  onDeleteOrigin(e) {
    this.orginSelected = undefined;
    this.sbtDisable = true;
  }

  hangleLanguageChange(e) {
    if (this.term) {
      let parms = new HttpParams()
        .set("size", this.pageSize)
        .set("lang", this.languageRadio)
        .set("term", this.term);
      this.getAirportsList(parms);
    } else {
      let parms = new HttpParams()
        .set("size", this.pageSize)
        .set("lang", this.languageRadio);
      this.getAirportsList(parms);
    }
  }
  getFare() {
    let params = {
      source: this.orginSelected.code,
      destination: this.destinationSelected.code,
      currency: this.currenyRadio,
    };
    let farelog = { ...this.log, requestType: environment.fareDetails };
    let present = performance.now();
    this.spinner = true;
    this.service.getFareDetails(params).subscribe(
      (data: IFares) => {
        this.spinner = false;
        this.fare = data.amount;
        this.showFare = true;
        this.fareCurrency = data.currency;
        this.spinner = false;
        farelog = {
          ...farelog,
          responseCode: 200,
          responseTime: performance.now() - present,
        };
        console.log(this.fareCurrency);
        this.service.logginResponseandrequest(farelog).subscribe(
          (data) => console.log(`logged response to the server`),
          (error: HttpErrorResponse) => {
            console.log(error);
            console.error(`Logger got failed to log resonse to server.`);
          }
        );
      },
      (error: HttpErrorResponse) => {
        farelog = {
          ...farelog,
          responseCode: error.status,
          responseTime: performance.now() - present,
        };

        this.service.logginResponseandrequest(farelog).subscribe(
          (data) => console.error(`saved response to the server`),
          (error: HttpErrorResponse) => {
            console.log(error);
            console.error(`failed to save resonse to server.`);
          }
        );
        console.error(error);
      }
    );
  }

  onPageChange(e) {
    if (this.currentPage !== e.page) {
      if (this.term) {
        let parms = new HttpParams()
          .set("size", this.pageSize)
          .set("lang", this.languageRadio)
          .set("page", e.page)
          .set("term", this.term);
        this.getAirportsList(parms);
      } else {
        let parms = new HttpParams()
          .set("size", this.pageSize)
          .set("lang", this.languageRadio)
          .set("page", e.page);
        this.getAirportsList(parms);
      }
    }
  }
}
