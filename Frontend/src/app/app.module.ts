import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {RecordComponent} from "./components/records/record.component";
import {RecordsListComponent} from "./components/records/records-list/records-list.component";
import {RecordDetailsComponent} from "./components/records/record-details/record-details.component";

@NgModule({
  declarations: [
    RecordComponent,
    RecordsListComponent,
    RecordDetailsComponent,
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
