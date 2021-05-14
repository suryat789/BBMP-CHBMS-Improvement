import { Moment } from 'moment';

export interface IPatientAudit {
  id?: number;
  patientId?: string;
  phone?: string;
  srfNumber?: string;
  bucode?: string;
  category?: string;
  zone?: string;
  queueType?: string;
  queueName?: string;
  timeAddedToQueue?: Moment;
  createdOn?: Moment;
  updatedOn?: Moment;
  updatedByMsgId?: string;
  auditedOn?: Moment;
}

export class PatientAudit implements IPatientAudit {
  constructor(
    public id?: number,
    public patientId?: string,
    public phone?: string,
    public srfNumber?: string,
    public bucode?: string,
    public category?: string,
    public zone?: string,
    public queueType?: string,
    public queueName?: string,
    public timeAddedToQueue?: Moment,
    public createdOn?: Moment,
    public updatedOn?: Moment,
    public updatedByMsgId?: string,
    public auditedOn?: Moment
  ) {}
}
