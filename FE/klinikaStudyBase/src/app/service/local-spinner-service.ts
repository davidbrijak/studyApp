import { Injectable, OnDestroy } from '@angular/core';
import {AbstractSpinnerBase} from "./abstract-spinner-base";

@Injectable({
  providedIn: 'any'
})
export class LocalSpinnerService extends AbstractSpinnerBase implements OnDestroy {

  protected showDelay = 0;
  protected hideDelay = 1000;

  ngOnDestroy(): void {
    super.destroy();
  }
}
