import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Category, DashboardSummary, Product, StockTransaction } from './models';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ApiService {
  private readonly http = inject(HttpClient);
  private readonly api = environment.apiUrl;

  dashboard(): Observable<DashboardSummary> {
    return this.http.get<DashboardSummary>(`${this.api}/dashboard/summary`);
  }

  categories(): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.api}/categories`);
  }

  createCategory(data: Category): Observable<Category> {
    return this.http.post<Category>(`${this.api}/categories`, data);
  }

  deleteCategory(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/categories/${id}`);
  }

  products(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.api}/products`);
  }

  lowStock(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.api}/products/low-stock`);
  }

  createProduct(data: Product): Observable<Product> {
    return this.http.post<Product>(`${this.api}/products`, data);
  }

  updateProduct(id: number, data: Product): Observable<Product> {
    return this.http.put<Product>(`${this.api}/products/${id}`, data);
  }

  deleteProduct(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/products/${id}`);
  }

  stockTransactions(): Observable<StockTransaction[]> {
    return this.http.get<StockTransaction[]>(`${this.api}/stock`);
  }

  addStock(productId: number, type: 'STOCK_IN' | 'STOCK_OUT', quantity: number, description: string): Observable<StockTransaction> {
    let params = new HttpParams()
      .set('type', type)
      .set('quantity', quantity);
    if (description.trim()) params = params.set('description', description.trim());
    return this.http.post<StockTransaction>(`${this.api}/stock/${productId}`, null, { params });
  }
}