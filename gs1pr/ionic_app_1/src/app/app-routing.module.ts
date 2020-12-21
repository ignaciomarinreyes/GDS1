import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'home',
    loadChildren: () => import('./home/home.module').then( m => m.HomePageModule)
  },
  {
    path: 'login',
    loadChildren: () => import('./pages/login/login.module').then( m => m.LoginPageModule)
  },
  {
    path: 'draw',
    loadChildren: () => import('./pages/draw/draw.module').then( m => m.DrawPageModule)
  },
  {
    path: 'main',
    loadChildren: () => import('./pages/main/main.module').then( m => m.MainPageModule)
  },
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'add-pop-over',
    loadChildren: () => import('./pages/add-pop-over/add-pop-over.module').then( m => m.AddPopOverPageModule)
  },
  {
    path: 'base-draw/:id',
    loadChildren: () => import('./pages/base-draw/base-draw.module').then( m => m.BaseDrawPageModule)
  },
  {
    path: 'my-routes',
    loadChildren: () => import('./pages/my-routes/my-routes.module').then( m => m.MyRoutesPageModule)
  },
  {
    path: 'profile',
    loadChildren: () => import('./pages/profile/profile.module').then( m => m.ProfilePageModule)
  },
  {
    path: 'search',
    loadChildren: () => import('./pages/search/search.module').then( m => m.SearchPageModule)
  },
  {
    path: 'user/:id',
    loadChildren: () => import('./pages/user/user.module').then( m => m.UserPageModule)
  },
  {
    path: 'toproutes',
    loadChildren: () => import('./pages/toproutes/toproutes.module').then( m => m.ToproutesPageModule)
  },
  {
    path: 'edit',
    loadChildren: () => import('./pages/edit/edit.module').then( m => m.EditPageModule)
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
