import { Moment } from 'moment';

export interface IBed {
  id?: number;
  bedId?: string;
  type?: string;
  capacity?: number;
  occupied?: number;
  blocked?: number;
  vacant?: number;
  createdOn?: Moment;
  updatedOn?: Moment;
  updatedByMsgId?: string;
  hospitalId?: number;
}

export class Bed implements IBed {
  constructor(
    public id?: number,
    public bedId?: string,
    public type?: string,
    public capacity?: number,
    public occupied?: number,
    public blocked?: number,
    public vacant?: number,
    public createdOn?: Moment,
    public updatedOn?: Moment,
    public updatedByMsgId?: string,
    public hospitalId?: number
  ) {}
}
