import { environment } from './../environments/environment.prod';
import { ILogResponse } from './models/LogResponse';
import { InterceptorService } from './interceptor.service';
import { IFares } from './models/Fares';
import { IAirports } from './models/AirPorts';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class ServicesService {
  header:HttpHeaders;
  constructor(private http:HttpClient,private intersept:InterceptorService) { }


  getAirPorts(data:any){

      return this.http.get<IAirports>(environment.airportsList,{params:data});

  }

  getFareDetails(data){
    let param=new HttpParams().set("currency",data.currency);
    console.log(param)
    return this.http.get<IFares>(environment.fareDetails+`${data.source}/${data.destination}`,{params:param});
  }
  logginResponseandrequest(data){

    return this.http.post<string>(environment.logginResponseTime,data);
  }
  getLoggedData(){
    return this.http.get<ILogResponse>(environment.loggedData);
  }

}
