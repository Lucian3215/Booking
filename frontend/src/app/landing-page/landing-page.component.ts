import { Component, OnInit, ViewChild, inject } from '@angular/core';
import { SearchBoxComponent } from './search-box/search-box.component';
import { MaterialModule } from '../core/material.module';
import { ApiService } from '../core/services/api.service';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { MatStepper } from '@angular/material/stepper';
import { interval } from 'rxjs';
import { CommonModule } from '@angular/common';
import { UserService } from '../core/services/user.service';
import { BookingService } from '../core/services/booking.service';

@Component({
  selector: 'app-landing-page',
  standalone: true,
  imports: [
    SearchBoxComponent,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
  ],
  templateUrl: './landing-page.component.html',
  styleUrl: './landing-page.component.scss',
})
export class LandingPageComponent implements OnInit {
  apiService = inject(ApiService);
  userService = inject(UserService);
  bookingService = inject(BookingService);

  range: any = new FormGroup({
    start: new FormControl<Date | null>(null),
    end: new FormControl<Date | null>(null),
  });

  firstStepFinished: boolean = false;
  secondStepFinished: boolean = false;
  destination: any;
  roomsCount = 1;
  adultsNumber = 1;
  destinationType: any;

  results: any;
  userId: any;

  ngOnInit(): void {
    this.userId = this.userService.getId();
  }

  onSearch() {
    let data = {
      pageNumber: 0,
      checkin: this.range.value.start,
      checkout: this.range.value.end,
      roomsCount: this.roomsCount,
      destination: this.destination,
      destinationType: this.destinationType,
      adultsNumber: this.adultsNumber,
    };
    this.apiService.getHotels(data).subscribe((res: any) => {
      this.results = res.result;
    });
  }

  onSelect(e: any, stepper: any) {
    this.destination = e.dest_id;
    this.destinationType = e.dest_type;
    this.firstStepFinished = true;
    interval(0).subscribe(() => stepper.next());
  }

  onSelectDate() {
    this.secondStepFinished = true;
  }

  onAddBookmark(data: any) {
    let booking = {
      userID: this.userId,
      sourceLink: data.url,
      startDate: this.range.value.start,
      endDate: this.range.value.end,
      price: `${data.min_total_price} ${data.currency_code}`,
    };
    let hotel = {
      name: data.hotel_name,
      address: `${data.address} ${data.city}, ${data.country_trans}`,
      imageUrl: data.main_photo_url,
      maxImageUrl: data.max_photo_url,
      description: data.unit_configuration_label,
      roomsCount: this.roomsCount,
    };
    this.bookingService.addBooking(booking, hotel);
  }
}
