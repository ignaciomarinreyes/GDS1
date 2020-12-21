import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { MyRoutesPage } from './my-routes.page';

describe('MyRoutesPage', () => {
  let component: MyRoutesPage;
  let fixture: ComponentFixture<MyRoutesPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyRoutesPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(MyRoutesPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
