import { Component, EventEmitter, Input, OnDestroy, Output } from '@angular/core';
import { Subscription } from 'rxjs';
import {LocalSpinnerService} from "../service/local-spinner-service";

@Component({
  selector: 'app-submit-button',
  templateUrl: './submit-button.component.html',
  styleUrls: ['./submit-button.component.scss'],
  providers: [LocalSpinnerService]
})
export class SubmitButtonComponent implements OnDestroy {

  @Input() disabled = false;
  @Output() loading: EventEmitter<boolean> = new EventEmitter();

  private readonly localSpinnerGetLoadingSub: Subscription;

  constructor(public localSpinnerService: LocalSpinnerService) {
    this.localSpinnerGetLoadingSub = this.localSpinnerService.getLoading().subscribe(loading => {
      this.loading.emit(loading);
    });
  }

  ngOnDestroy(): void {
    this.localSpinnerGetLoadingSub?.unsubscribe();
  }

  onClick(): void {
    this.localSpinnerService.show();
  }

  getLoading(): boolean {
    return this.localSpinnerService.loading;
  }

  stopSpinner(): void {
    this.localSpinnerService.hide();
  }
}
