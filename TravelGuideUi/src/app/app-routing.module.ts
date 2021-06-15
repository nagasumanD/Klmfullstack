import { StatisticsComponent } from './statistics/statistics.component';
import { FareComponent } from "./fare/fare.component";
import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

const routes: Routes = [
  { path: "", component: FareComponent },
  { path: "fareestimate", component: FareComponent },
  { path: "Statistics", component: StatisticsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
