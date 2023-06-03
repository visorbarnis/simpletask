import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {RecordItem} from "../../../models/recordItem.model";
import {RecordService} from "../../../services/record.service";
import {RecordStatus2LabelMapping, RecordStatusEnum} from "../../../models/recordStatus.enum";

@Component({
  selector: 'app-record-details',
  templateUrl: './record-details.component.html',
  styleUrls: ['./record-details.component.css']
})
export class RecordDetailsComponent implements OnInit {

  @Input() viewMode = false;

  @Input() currentRecordItem: RecordItem = {
    id: '',
    name: '',
    description: '',
    dueDate: new Date,
    markDate: new Date,
    creationDate: new Date,
    status: RecordStatusEnum.NOT_DONE
  };

  message = '';

  public recordStatus2LabelMapping = RecordStatus2LabelMapping;
//  public recordStatusTypes = Object.values(RecordStatusEnum);

  public recordStatusTypes =   Object.values(RecordStatusEnum).filter(value => typeof value === 'string');

  constructor(
    private recordService: RecordService,
    private route: ActivatedRoute,
    private router: Router) {

  }


  onSelectedStatus(value: string): void {
  }

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getRecordItem(this.route.snapshot.params["id"]);
    }
  }

  getRecordItem(id: string): void {
    this.recordService.get(id)
      .subscribe({
        next: (data) => {
          this.currentRecordItem = data;
        },
        error: (e) => console.error(e)
      });
  }

  updateRecordItem(): void {
    this.message = '';
    this.recordService.update(this.currentRecordItem.id, this.currentRecordItem)
      .subscribe({
        next: (res) => {
          this.message = res.message ? res.message : 'This record was updated successfully!';
        },
        error: (e) => console.error(e)
      });
  }

  deleteRecordItem(): void {
    this.recordService.delete(this.currentRecordItem.id)
      .subscribe({
        next: () => {
          this.router.navigate(['/records']);
        },
        error: (e) => console.error(e)
      });
  }

  protected readonly RecordStatusEnum = RecordStatusEnum;
}
