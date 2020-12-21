import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { AddPopOverPage } from './add-pop-over.page';

describe('AddPopOverPage', () => {
  let component: AddPopOverPage;
  let fixture: ComponentFixture<AddPopOverPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddPopOverPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(AddPopOverPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
