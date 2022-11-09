import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';


import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {WelcomeComponent} from './welcome/welcome.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgMultiSelectDropDownModule} from "ng-multiselect-dropdown";
import {ErrorComponent} from './error/error.component';
import {SzerepkorListComponent} from './szerepkor-list/szerepkor-list.component';
import {MenuComponent} from './menu/menu.component';
import {FooterComponent} from './footer/footer.component';
import {HttpClientModule} from "@angular/common/http";
import {UjSzerepkorComponent} from './szerepkor-list/uj-szerepkor/uj-szerepkor.component';
import {MatTableModule} from "@angular/material/table";
import {MatSortModule} from "@angular/material/sort";
import {MatPaginatorModule} from "@angular/material/paginator";
import {BrowserAnimationsModule, NoopAnimationsModule} from "@angular/platform-browser/animations";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {FizetesiPeriodusListComponent} from './fizetesi-periodus-list/fizetesi-periodus-list.component';
import {UjFizetesiPeriodusComponent} from './fizetesi-periodus-list/uj-fizetesi-periodus/uj-fizetesi-periodus.component';
import {SzervezetListComponent} from './szervezet-list/szervezet-list.component';
import {SzervezetTipusListComponent} from './szervezet-tipus-list/szervezet-tipus-list.component';
import {UjSzervezetTipusComponent} from './szervezet-tipus-list/uj-szervezet-tipus/uj-szervezet-tipus.component';
import {UjSzervezetComponent} from './szervezet-list/uj-szervezet/uj-szervezet.component';
import {MatSelectModule} from "@angular/material/select";
import {StudyComponent} from './study/study.component';
import {JogosultsagListComponent} from './jogosultsag/jogosultsag-list.component';
import {UjJogosultsagComponent} from './jogosultsag/uj-jogosultsag/uj-jogosultsag.component';
import {VizsgalatiModComponent} from './vizsgalati-mod/vizsgalati-mod.component';
import {UjVizsgalatiModComponent} from './vizsgalati-mod/uj-vizsgalati-mod/uj-vizsgalati-mod.component';
import {UjStudyComponent} from './study/uj-study/uj-study.component';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {MatInputModule} from "@angular/material/input";
import { MeresComponent } from './meres/meres.component';
import { UjMeresComponent } from './meres/uj-meres/uj-meres.component';
import { SzemelyComponent } from './szemely/szemely.component';
import { UjSzemelyComponent } from './szemely/uj-szemely/uj-szemely.component';
import {NgSelectModule} from "@ng-select/ng-select";
import { KifizetesekComponent } from './kifizetesek/kifizetesek.component';
import { KifizetesDetailComponent } from './kifizetesek/kifizetes-detail/kifizetes-detail.component';
import {MatCheckboxModule} from "@angular/material/checkbox";
import { AlertModalComponent } from './alert-modal/alert-modal.component';
import { DokumentumokKezeleseComponent } from './dokumentumok-kezelese/dokumentumok-kezelese.component';
import { FileUploadComponentComponent } from './file-upload-component/file-upload-component.component';
import { SzemelyekListComponent } from './szemelyek-list/szemelyek-list.component';
import { LoginComponent } from './login/login.component';
import { SubmitButtonComponent } from './submit-button/submit-button.component';
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import { LoginPageComponent } from './login-page/login-page.component';
import {MatTabsModule} from "@angular/material/tabs";
import {TranslateModule} from "@ngx-translate/core";
import {MatSnackBar, MatSnackBarModule} from "@angular/material/snack-bar";
import {environment} from "../environments/environment";

@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    ErrorComponent,
    SzerepkorListComponent,
    MenuComponent,
    FooterComponent,
    UjSzerepkorComponent,
    FizetesiPeriodusListComponent,
    UjFizetesiPeriodusComponent,
    SzervezetListComponent,
    SzervezetTipusListComponent,
    UjSzervezetTipusComponent,
    UjSzervezetComponent,
    StudyComponent,
    JogosultsagListComponent,
    UjJogosultsagComponent,
    VizsgalatiModComponent,
    UjVizsgalatiModComponent,
    UjStudyComponent,
    MeresComponent,
    UjMeresComponent,
    SzemelyComponent,
    UjSzemelyComponent,
    KifizetesekComponent,
    KifizetesDetailComponent,
    AlertModalComponent,
    DokumentumokKezeleseComponent,
    FileUploadComponentComponent,
    SzemelyekListComponent,
    LoginComponent,
    SubmitButtonComponent,
    LoginPageComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    NgSelectModule,
    NgMultiSelectDropDownModule,
    BrowserAnimationsModule,
    NoopAnimationsModule,
    MatIconModule,
    MatSnackBarModule,
    MatButtonModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatInputModule,
    ReactiveFormsModule,
    MatCheckboxModule,
    MatProgressSpinnerModule,
    MatTabsModule,
    TranslateModule.forRoot(),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {


}

