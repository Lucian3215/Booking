import { Component, OnInit, inject } from '@angular/core';
import { BookingService } from '../core/services/booking.service';
import { UserService } from '../core/services/user.service';
import { AsyncPipe, JsonPipe, NgIf } from '@angular/common';
import { Observable } from 'rxjs';
import { MaterialModule } from '../core/material.module';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [NgIf, AsyncPipe, MaterialModule],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss',
})
export class CartComponent implements OnInit {
  bookingService = inject(BookingService);
  userService = inject(UserService);

  bookmarks$!: Observable<any>;

  ngOnInit(): void {
    let userId = this.userService.getId();
    this.bookmarks$ = this.bookingService.getBookings(userId);
  }

  onDelete(bookmark: any) {
    this.bookingService.deleteBooking(bookmark.id);
  }
}
