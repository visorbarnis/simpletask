import {Component, OnInit} from '@angular/core';
import {RecordService} from "../../services/record.service";
import {RecordItem} from "../../models/recordItem.model";
import {FormControl, Validators} from "@angular/forms";
import {RecordStatusEnum} from "../../models/recordStatus.enum";

@Component({
  selector: 'app-add-record',
  templateUrl: './record.component.html',
  styleUrls: ['./record.component.css']
})
export class RecordComponent implements OnInit {

  currentRecord: RecordItem = {
    id: 0,
    name: '',
    description: '',
    dueDate: new Date,
    markDate: new Date,
    creationDate: new Date,
    status: RecordStatusEnum.NOT_DONE

  };


  submitted = false;
  errorMessage: string ='';

  constructor(private recordService: RecordService) {
  }

  ngOnInit(): void {
  }

  saveRecord(): void {
    const data = this.currentRecord;

    this.recordService.create(data)
      .subscribe({
        next: () => {
          this.submitted = true;
        },
        error: (e) => {console.error(e);
          this.errorMessage=" Not submitted. Incorrect data ..."; }
      });
  }

  createRecord(): void {
    this.submitted = false;
    this.currentRecord = {
      id: '',
      name: '',
      description: '',
      dueDate: new Date,
      markDate: new Date,
      creationDate: new Date,
      status: RecordStatusEnum.NOT_DONE
    };
  }


  dateTimeControl = new FormControl('', [Validators.required, Validators.pattern(/^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}$/)]);

  protected readonly RecordStatusEnum = RecordStatusEnum;
}
