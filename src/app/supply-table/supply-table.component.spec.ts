import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupplyTableComponent } from './supply-table.component';

describe('SupplyTableComponent', () => {
  let component: SupplyTableComponent;
  let fixture: ComponentFixture<SupplyTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SupplyTableComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SupplyTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
