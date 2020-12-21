import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { ToproutesPage } from './toproutes.page';

describe('ToproutesPage', () => {
  let component: ToproutesPage;
  let fixture: ComponentFixture<ToproutesPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ToproutesPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(ToproutesPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
