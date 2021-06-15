import { Component, Input, OnInit, Output } from "@angular/core";
import { EventEmitter } from "@angular/core";

@Component({
  selector: "app-airport-view",
  templateUrl: "./airport-view.component.html",
  styleUrls: ["./airport-view.component.css"],
})
export class AirportViewComponent implements OnInit {
  @Input() remove: boolean = false;
  @Input() airport: any;
  @Input() show: boolean = false;
  @Output() onClose = new EventEmitter();
  constructor() {}

  ngOnInit(): void {}

  toggleCard(event) {
    event.stopPropagation();
    this.show = !this.show;
  }
  deleteAction() {
    this.onClose.emit(this.airport.code);
  }
}
