import { Injectable } from '@angular/core';
import { MatSnackBar, MatSnackBarRef, TextOnlySnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class NotifierService {

  constructor(private snackbar: MatSnackBar) {
  }

  showMessage(message: string, action = 'X', duration = 5000): MatSnackBarRef<TextOnlySnackBar> {
    return this.snackbar.open(message, action, {
      duration
    });
  }
}
