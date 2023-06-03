import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {RecordService} from "../../../services/record.service";
import {RecordItem} from "../../../models/recordItem.model";

@Component({
  selector: 'app-records-list',
  templateUrl: './records-list.component.html',
  styleUrls: ['./records-list.component.css']
})
export class RecordsListComponent implements OnInit {

  records?: RecordItem[];
  currentRecordItem: RecordItem = {};
  currentIndex = -1;
  recordName = '';

  constructor(private recordService: RecordService, private _router: Router) {
  }

  ngOnInit(): void {
    this.retrieveContracts();
  }

  retrieveContracts(): void {
    this.recordService.getAll()
      .subscribe({
        next: (data) => {
          this.records = data;
        },
        error: (e) => console.error(e)
      });
  }

  refreshList(): void {
    this.retrieveContracts();
    this.currentRecordItem = {};
    this.currentIndex = -1;
  }

  setActiveRecord(recordItem: RecordItem, index: number): void {
    this.currentRecordItem = recordItem;
    this.currentIndex = index;
  }

  removeAllContracts(): void {
    this.recordService.deleteAll()
      .subscribe({
        next: () => {
          this.refreshList();
        },
        error: (e) => console.error(e)
      });
  }

  searchRecordName(): void {
    this.currentRecordItem = {};
    this.currentIndex = -1;

    this.recordService.findByRecordName(this.recordName)
      .subscribe({
        next: (data) => {
          this.records = data;
        },
        error: (e) => console.error(e)
      });
  }

  navigateToAdd(): void {
    this._router.navigateByUrl('/add')
  }

  showAll(): void {
    this.retrieveContracts();
  }

  showNotDone(): void {
    this.currentRecordItem = {};
    this.currentIndex = -1;

    this.recordService.findByRecordNotDoneStatus()
      .subscribe({
        next: (data) => {
          this.records = data;
        },
        error: (e) => console.error(e)
      });


  }


}
