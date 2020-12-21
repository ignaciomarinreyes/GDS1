import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { BaseDrawPage } from './base-draw.page';

describe('BaseDrawPage', () => {
  let component: BaseDrawPage;
  let fixture: ComponentFixture<BaseDrawPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BaseDrawPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(BaseDrawPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
