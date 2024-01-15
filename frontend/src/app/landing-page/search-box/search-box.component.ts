import {
  Component,
  ElementRef,
  EventEmitter,
  OnInit,
  Output,
  ViewChild,
  inject,
} from '@angular/core';
import { MaterialModule } from '../../core/material.module';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import {
  Observable,
  debounce,
  debounceTime,
  distinctUntilChanged,
  filter,
  map,
  of,
  startWith,
  tap,
} from 'rxjs';
import { ApiService } from '../../core/services/api.service';

@Component({
  selector: 'app-search-box',
  standalone: true,
  imports: [MaterialModule, FormsModule, CommonModule, ReactiveFormsModule],
  templateUrl: './search-box.component.html',
  styleUrl: './search-box.component.scss',
})
export class SearchBoxComponent implements OnInit {
  @Output() select = new EventEmitter();
  @ViewChild('input') input: any;

  locations: any;

  apiService = inject(ApiService);

  myControl = new FormControl('');
  options: string[] = [];
  filteredOptions: Observable<string[]> | undefined;
  searching: boolean = false;

  ngOnInit() {
    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(''),
      distinctUntilChanged(),
      filter((value: string | null) => value!?.length > 2),
      map((value) => this._filter(value || ''))
    );
  }

  private _filter(value: string): string[] {
    this.searching = true;
    this.apiService.getLocations(value).subscribe((res) => {
      this.options = [];
      res.forEach((el: any) => {
        this.options.push(el.label);
      });
      this.locations = res;
      this.searching = false;
    });
    const filterValue = value.toLowerCase();
    return this.options.filter((option) =>
      option
        .toLowerCase()
        .normalize('NFD')
        .replace(/[\u0300-\u036f]/g, '')
        .includes(filterValue.normalize('NFD').replace(/[\u0300-\u036f]/g, ''))
    );
  }

  filter(): void {
    const filterValue = this.input.nativeElement.value.toLowerCase();
    this.filteredOptions = of(
      this.options.filter((option) =>
        option
          .toLowerCase()
          .normalize('NFD')
          .replace(/[\u0300-\u036f]/g, '')
          .includes(
            filterValue.normalize('NFD').replace(/[\u0300-\u036f]/g, '')
          )
      )
    );
  }

  onSelect(option: any) {
    this.select.emit(this.locations.filter((el: any) => el.label == option)[0]);
  }
}
