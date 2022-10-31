import { Observable, Subject, Subscription, timer } from 'rxjs';
import { debounce } from 'rxjs/operators';

export abstract class AbstractSpinnerBase {

  loading = false;

  protected showDelay = 1000;
  protected hideDelay = 0;
  protected loading$: Subject<boolean> = new Subject();

  protected readonly loadingWithDelaySub: Subscription;

  constructor() {
    this.loadingWithDelaySub = this.getLoading().subscribe(res => {
      this.loading = res;
    });
  }

  setShowDelay(delayInMs: number): void {
    this.showDelay = delayInMs;
  }

  setHideDelay(delayInMs: number): void {
    this.hideDelay = delayInMs;
  }

  getLoading(): Observable<boolean> {
    return this.loading$.pipe(
      debounce(val => val ? timer(this.showDelay) : timer(this.hideDelay))
    );
  }

  show(): void {
    this.loading$.next(true);
  }

  hide(): void {
    this.loading$.next(false);
  }

  protected destroy(): void {
    this.loadingWithDelaySub?.unsubscribe();
  }
}
