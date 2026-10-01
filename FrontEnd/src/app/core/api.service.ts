import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private readonly API_URL = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  // =========================================================
  // DASHBOARD
  // =========================================================

  dashboard(): Observable<any> {
    return this.http.get<any>(
        `${this.API_URL}/dashboard/summary`
    );
  }

  lowStock(): Observable<any> {
    return this.http.get<any>(
        `${this.API_URL}/products/low-stock`
    );
  }

  // =========================================================
  // CATEGORIES
  // =========================================================

  categories(): Observable<any> {
    return this.http.get<any>(
        `${this.API_URL}/categories`
    );
  }

  getCategory(id: number): Observable<any> {
    return this.http.get<any>(
        `${this.API_URL}/categories/${id}`
    );
  }

  createCategory(category: any): Observable<any> {
    return this.http.post<any>(
        `${this.API_URL}/categories`,
        category
    );
  }

  updateCategory(
      id: number,
      category: any
  ): Observable<any> {
    return this.http.put<any>(
        `${this.API_URL}/categories/${id}`,
        category
    );
  }

  deleteCategory(id: number): Observable<any> {
    return this.http.delete<any>(
        `${this.API_URL}/categories/${id}`
    );
  }

  // =========================================================
  // PRODUCTS
  // =========================================================

  products(): Observable<any> {
    return this.http.get<any>(
        `${this.API_URL}/products`
    );
  }

  getProduct(id: number): Observable<any> {
    return this.http.get<any>(
        `${this.API_URL}/products/${id}`
    );
  }

  createProduct(product: any): Observable<any> {
    return this.http.post<any>(
        `${this.API_URL}/products`,
        product
    );
  }

  updateProduct(
      id: number,
      product: any
  ): Observable<any> {
    return this.http.put<any>(
        `${this.API_URL}/products/${id}`,
        product
    );
  }

  deleteProduct(id: number): Observable<any> {
    return this.http.delete<any>(
        `${this.API_URL}/products/${id}`
    );
  }

  // =========================================================
  // STOCK
  // =========================================================

  stockTransactions(): Observable<any> {
    return this.http.get<any>(
        `${this.API_URL}/stock`
    );
  }

  stockForProduct(productId: number): Observable<any> {
    return this.http.get<any>(
        `${this.API_URL}/stock/${productId}`
    );
  }

  addStock(
      productId: number,
      type: string,
      quantity: number,
      description: string
  ): Observable<any> {

    let params = new HttpParams()
        .set('type', type)
        .set('quantity', quantity.toString());

    if (description && description.trim()) {
      params = params.set(
          'description',
          description.trim()
      );
    }

    return this.http.post<any>(
        `${this.API_URL}/stock/${productId}`,
        null,
        {
          params
        }
    );
  }

}