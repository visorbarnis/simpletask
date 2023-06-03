export enum RecordStatusEnum {

  NOT_DONE,DONE,PAST_DUE

}

// optional: Record type annotation guaranties that
// all the values from the enum are presented in the mapping
export const RecordStatus2LabelMapping: Record<RecordStatusEnum, string> = {
  [RecordStatusEnum.DONE]: "Done",
  [RecordStatusEnum.NOT_DONE]: "No done",
  [RecordStatusEnum.PAST_DUE]: "Past due"
};
