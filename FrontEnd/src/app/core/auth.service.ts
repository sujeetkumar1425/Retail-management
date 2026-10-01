import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly API_URL = 'http://localhost:8080/api/auth';

  user: any = null;

  constructor(private http: HttpClient) {
    this.loadUser();
  }

  login(email: string, password: string): Observable<any> {
    return this.http.post<any>(
        `${this.API_URL}/login`,
        {
          email,
          password
        }
    ).pipe(
        tap(response => {

          if (response.token) {
            localStorage.setItem('token', response.token);
          }

          this.user = {
            id: response.id,
            name: response.name,
            email: response.email,
            role: response.role
          };

          localStorage.setItem(
              'user',
              JSON.stringify(this.user)
          );
        })
    );
  }

  register(
      name: string,
      email: string,
      password: string
  ): Observable<any> {

    return this.http.post<any>(
        `${this.API_URL}/register`,
        {
          name,
          email,
          password
        }
    );
  }

  private loadUser(): void {

    const storedUser = localStorage.getItem('user');

    if (storedUser) {
      try {
        this.user = JSON.parse(storedUser);
      } catch {
        this.user = null;
      }
    }
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  isAdmin(): boolean {
    return this.user?.role === 'ADMIN';
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('user');

    this.user = null;
  }
}