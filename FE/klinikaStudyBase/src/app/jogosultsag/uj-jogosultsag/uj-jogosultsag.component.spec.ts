import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UjJogosultsagComponent } from './uj-jogosultsag.component';

describe('UjJogosultsagComponent', () => {
  let component: UjJogosultsagComponent;
  let fixture: ComponentFixture<UjJogosultsagComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UjJogosultsagComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UjJogosultsagComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
