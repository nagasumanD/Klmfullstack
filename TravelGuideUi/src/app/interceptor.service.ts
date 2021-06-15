import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { retry } from "rxjs/operators";

@Injectable({
  providedIn: "root",
})
export class InterceptorService implements HttpInterceptor {
  intercept(
    httpRequest: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    console.log(
      `Retrying ....`
    );
    return next.handle(httpRequest).pipe(retry(2));
  }

  constructor() {}
}
