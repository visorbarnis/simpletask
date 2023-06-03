import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {RecordItem} from "../models/recordItem.model";

//const baseUrl = '/records';
const baseUrl = 'http://localhost:8080/records';



@Injectable({
  providedIn: 'root'
})
export class RecordService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<RecordItem[]> {
    return this.http.get<RecordItem[]>(baseUrl);
  }

  getItemList(): Observable<RecordItem[]> {
    return this.http.get<RecordItem[]>(`${baseUrl}/list`);
  }

  get(id: any): Observable<RecordItem> {
    return this.http.get(`${baseUrl}/${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }

  findByRecordName(recordName: any): Observable<RecordItem[]> {
    return this.http.get<RecordItem[]>(`${baseUrl}/find?record_name=${recordName}`);
  }

  findByRecordNotDoneStatus(): Observable<RecordItem[]> {
    return this.http.get<RecordItem[]>(`${baseUrl}/find_not_done`);
  }


}
