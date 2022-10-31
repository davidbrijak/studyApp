import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {WelcomeComponent} from "./welcome/welcome.component";
import {ErrorComponent} from "./error/error.component";
import {SzerepkorListComponent} from "./szerepkor-list/szerepkor-list.component";
import {UjSzerepkorComponent} from "./szerepkor-list/uj-szerepkor/uj-szerepkor.component";
import {FizetesiPeriodusListComponent} from "./fizetesi-periodus-list/fizetesi-periodus-list.component";
import {UjFizetesiPeriodusComponent} from "./fizetesi-periodus-list/uj-fizetesi-periodus/uj-fizetesi-periodus.component";
import {SzervezetListComponent} from "./szervezet-list/szervezet-list.component";
import {SzervezetTipusListComponent} from "./szervezet-tipus-list/szervezet-tipus-list.component";
import {UjSzervezetTipusComponent} from "./szervezet-tipus-list/uj-szervezet-tipus/uj-szervezet-tipus.component";
import {UjSzervezetComponent} from "./szervezet-list/uj-szervezet/uj-szervezet.component";
import {StudyComponent} from "./study/study.component";
import {JogosultsagListComponent} from "./jogosultsag/jogosultsag-list.component";
import {UjJogosultsagComponent} from "./jogosultsag/uj-jogosultsag/uj-jogosultsag.component";
import {VizsgalatiModComponent} from "./vizsgalati-mod/vizsgalati-mod.component";
import {UjStudyComponent} from "./study/uj-study/uj-study.component";
import {UjMeresComponent} from "./meres/uj-meres/uj-meres.component";
import {UjSzemelyComponent} from "./szemely/uj-szemely/uj-szemely.component";
import {UjVizsgalatiModComponent} from "./vizsgalati-mod/uj-vizsgalati-mod/uj-vizsgalati-mod.component";
import {KifizetesekComponent} from "./kifizetesek/kifizetesek.component";
import {KifizetesDetailComponent} from "./kifizetesek/kifizetes-detail/kifizetes-detail.component";
import {FileUploadComponentComponent} from "./file-upload-component/file-upload-component.component";
import {SzemelyekListComponent} from "./szemelyek-list/szemelyek-list.component";
import {LoginPageComponent} from "./login-page/login-page.component";

const routes: Routes = [
  {path: 'auth/login', component: LoginPageComponent},
  {path: 'welcome', component: WelcomeComponent, canActivate: []},
  {path: 'szerepkor/list', component: SzerepkorListComponent, canActivate: []},
  {path: 'szemely/new', component: UjSzemelyComponent, canActivate: []},
  {path: 'fizetesiPeriodus/list', component: FizetesiPeriodusListComponent, canActivate: []},
  {path: 'szervezet/list', component: SzervezetListComponent, canActivate: []},
  {path: 'szervezetTipus/list', component: SzervezetTipusListComponent, canActivate: []},
  {path: 'kifizetesek/list', component: KifizetesekComponent, canActivate: []},
  {path: 'szemelyek/list', component: SzemelyekListComponent, canActivate: []},
  {path: 'jogosultsag/list', component: JogosultsagListComponent, canActivate: []},
  {path: 'study/list', component: StudyComponent, canActivate: []},
  {path: 'vizsgalatiMod/list', component: VizsgalatiModComponent, canActivate: []},
  {path: 'szerepkor/new/:id/:isDisabled', component: UjSzerepkorComponent, canActivate: []},
  {path: 'kif/detail/:id', component: KifizetesDetailComponent, canActivate: []},
  {path: 'study/new/:id/:isDisabled', component: UjStudyComponent, canActivate: []},
  {path: 'szervezetTipus/new/:id/:isDisabled', component: UjSzervezetTipusComponent, canActivate: []},
  {path: 'ujMeres/new/:studyId/:id/:isDisabled', component: UjMeresComponent, canActivate: []},
  {path: 'szervezet/new/:id', component: UjSzervezetComponent, canActivate: []},
  {path: 'vm/new/:id', component: UjVizsgalatiModComponent , canActivate: []},
  {path: 'fizetesiPeriodus/new/:id/:isDisabled', component: UjFizetesiPeriodusComponent, canActivate: []},
  {path: 'jogosultsag/new/:id', component: UjJogosultsagComponent, canActivate: []},
  {path: 'dokumentumok/:id', component: FileUploadComponentComponent, canActivate: []},
  //{path: '**', component: ErrorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
