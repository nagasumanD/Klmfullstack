import { Component, Input, OnInit, Output, EventEmitter } from "@angular/core";

@Component({
  selector: "app-pagination",
  templateUrl: "./pagination.component.html",
  styleUrls: ["./pagination.component.css"],
})
export class PaginationComponent implements OnInit {
  @Input() totalPages: number;
  @Output() onChangePageClick = new EventEmitter();
  selectedPage: number;
  upperBound: number = 12;
  lowerBound: number = 0;
  totalpaging: any;
  displayedPages: any;
  constructor() {
    this.totalpaging = this.counter(this.totalPages);
    this.displayedPages = this.totalpaging.slice(
      this.lowerBound,
      this.upperBound
    );
  }
  counter(range: number) {
    return Array(range)
      .fill(1)
      .map((x, y) => x + y);
  }
  ngOnInit(): void {
    this.displayedPages = this.totalpaging.slice(
      this.lowerBound,
      this.upperBound
    );
  }
  doChangePage(e) {
    this.selectedPage = e;
    this.onChangePageClick.emit({ page: e });
  }
  onPageIncrement() {
    this.upperBound += 1;
    this.lowerBound += 1;
  }
  onPageDecrement() {
    this.upperBound + -1;
    this.lowerBound -= 1;
  }
}
