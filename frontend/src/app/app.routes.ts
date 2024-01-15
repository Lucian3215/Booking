import { Routes } from '@angular/router';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { CartComponent } from './cart/cart.component';

export const routes: Routes = [
  { path: '', component: LandingPageComponent },
  { path: 'bookmarks', component: CartComponent },
];
