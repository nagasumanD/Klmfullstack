import { ILogResponse } from "./../models/LogResponse";
import { ServicesService } from "./../services.service";
import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-statistics",
  templateUrl: "./statistics.component.html",
  styleUrls: ["./statistics.component.css"],
})
export class StatisticsComponent implements OnInit {
  logData: ILogResponse;
  spinner: boolean = false;
  constructor(private service: ServicesService) {}

  ngOnInit(): void {
    this.spinner = true;
    this.service.getLoggedData().subscribe((data) => {
      this.spinner = false;
      this.logData = data;
    });
  }
}
