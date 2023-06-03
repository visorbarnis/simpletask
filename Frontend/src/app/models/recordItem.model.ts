import {RecordStatusEnum} from "./recordStatus.enum";


export class RecordItem {
  id?: any;
  name?: string;
  description?: string;
  dueDate?: Date;
  markDate?: Date;
  creationDate?: Date;
  status?: RecordStatusEnum;

}
