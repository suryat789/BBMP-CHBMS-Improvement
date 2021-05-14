import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBedAudit } from 'app/shared/model/bed-audit.model';

@Component({
  selector: 'jhi-bed-audit-detail',
  templateUrl: './bed-audit-detail.component.html',
})
export class BedAuditDetailComponent implements OnInit {
  bedAudit: IBedAudit | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bedAudit }) => (this.bedAudit = bedAudit));
  }

  previousState(): void {
    window.history.back();
  }
}
