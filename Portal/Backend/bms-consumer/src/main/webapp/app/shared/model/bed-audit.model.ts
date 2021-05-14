import { Moment } from 'moment';

export interface IBedAudit {
  id?: number;
  hospitalId?: string;
  bedId?: string;
  type?: string;
  capacity?: number;
  occupied?: number;
  blocked?: number;
  vacant?: number;
  createdOn?: Moment;
  updatedOn?: Moment;
  updatedByMsgId?: string;
  auditedOn?: Moment;
}

export class BedAudit implements IBedAudit {
  constructor(
    public id?: number,
    public hospitalId?: string,
    public bedId?: string,
    public type?: string,
    public capacity?: number,
    public occupied?: number,
    public blocked?: number,
    public vacant?: number,
    public createdOn?: Moment,
    public updatedOn?: Moment,
    public updatedByMsgId?: string,
    public auditedOn?: Moment
  ) {}
}
