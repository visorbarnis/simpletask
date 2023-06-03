import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RecordsListComponent} from "./components/records/records-list/records-list.component";
import {RecordDetailsComponent} from "./components/records/record-details/record-details.component";
import {RecordComponent} from "./components/records/record.component";

const routes: Routes = [
  { path: '', redirectTo: 'records', pathMatch: 'full' },
  { path: 'records', component: RecordsListComponent },
   { path: 'records/:id', component: RecordDetailsComponent },
   { path: 'add', component: RecordComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
