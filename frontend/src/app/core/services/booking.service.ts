import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root',
})
export class BookingService {
  apiURL = 'http://localhost:8080/api/';
  http = inject(HttpClient);
  snackBar = inject(MatSnackBar);

  getBookings(userId: string) {
    return this.http.get(this.apiURL + 'bookings/get/' + userId);
  }

  addBooking(booking: any, hotel: any) {
    this.http
      .post(this.apiURL + 'bookings/add', booking)
      .subscribe((res: any) => {
        this.http
          .post(this.apiURL + 'hotel/add/' + res.id, hotel)
          .subscribe(() => {
            this.snackBar.open('Added to bookmarks', '', {
              duration: 5000,
            });
          });
      });
  }

  deleteBooking(id: string) {
    this.http.delete(this.apiURL + 'bookings/delete/' + id).subscribe(() => {
      this.snackBar.open('Deleted', '', {
        duration: 5000,
      });
    });
  }
}
