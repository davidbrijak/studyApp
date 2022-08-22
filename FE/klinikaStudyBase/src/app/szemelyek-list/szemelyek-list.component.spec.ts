import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SzemelyekListComponent } from './szemelyek-list.component';

describe('SzemelyekListComponent', () => {
  let component: SzemelyekListComponent;
  let fixture: ComponentFixture<SzemelyekListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SzemelyekListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SzemelyekListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
