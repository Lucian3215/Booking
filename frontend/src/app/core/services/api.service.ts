import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private apiUrl = 'https://booking-com.p.rapidapi.com/v1/hotels/';

  constructor(private http: HttpClient) {}

  getLocations(value: string): Observable<any> {
    const headers = new HttpHeaders({
      'X-RapidAPI-Key': 'f6c6175897msh02c10adf25009efp1cc9f4jsna43db26d79f3',
      'X-RapidAPI-Host': 'booking-com.p.rapidapi.com',
    });

    const params = new HttpParams().set('name', value).set('locale', 'ro');

    return this.http.get(this.apiUrl + 'locations', { headers, params });
  }

  getHotels(data: any) {
    function join(date: any, options: any, separator: any) {
      function format(option: any) {
        let formatter = new Intl.DateTimeFormat('en', option);
        return formatter.format(date);
      }
      return options.map(format).join(separator);
    }

    let options = [
      { year: 'numeric' },
      { month: 'numeric' },
      { day: 'numeric' },
    ];
    const headers = new HttpHeaders({
      'X-RapidAPI-Key': 'e3e39e8971mshd3fdfb866bf349bp13eff6jsn5c5e3cf7cf13',
      'X-RapidAPI-Host': 'booking-com.p.rapidapi.com',
    });

    const params = new HttpParams()
      .set('order_by', 'popularity')
      .set('checkout_date', join(data.checkout, options, '-'))
      .set('filter_by_currency', 'RON')
      .set('locale', 'ro')
      .set('units', 'metric')
      .set('dest_id', data.destination)
      .set('dest_type', data.destinationType)
      .set('adults_number', data.adultsNumber)
      .set('room_number', data.roomsCount)
      .set('checkin_date', join(data.checkin, options, '-'))
      .set('page_number', data.pageNumber);
    console.log(params);
    return this.http.get(this.apiUrl + 'search', { headers, params });
  }
}
