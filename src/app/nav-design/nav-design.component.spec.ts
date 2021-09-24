import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavDesignComponent } from './nav-design.component';

describe('NavDesignComponent', () => {
  let component: NavDesignComponent;
  let fixture: ComponentFixture<NavDesignComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NavDesignComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NavDesignComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
