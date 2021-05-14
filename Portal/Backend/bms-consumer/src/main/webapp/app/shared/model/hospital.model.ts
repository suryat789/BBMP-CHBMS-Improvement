import { Moment } from 'moment';
import { IBed } from 'app/shared/model/bed.model';

export interface IHospital {
  id?: number;
  hospitalId?: string;
  type?: string;
  name?: string;
  address?: string;
  pincode?: number;
  phone?: string;
  zone?: string;
  status?: string;
  createdOn?: Moment;
  updatedOn?: Moment;
  updatedByMsgId?: string;
  ids?: IBed[];
}

export class Hospital implements IHospital {
  constructor(
    public id?: number,
    public hospitalId?: string,
    public type?: string,
    public name?: string,
    public address?: string,
    public pincode?: number,
    public phone?: string,
    public zone?: string,
    public status?: string,
    public createdOn?: Moment,
    public updatedOn?: Moment,
    public updatedByMsgId?: string,
    public ids?: IBed[]
  ) {}
}
