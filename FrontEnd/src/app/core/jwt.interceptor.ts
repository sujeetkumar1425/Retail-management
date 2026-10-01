import { HttpInterceptorFn } from '@angular/common/http';

export const jwtInterceptor: HttpInterceptorFn = (req, next) => {

  // Get JWT token from localStorage
  const token = localStorage.getItem('token');

  // Login and register do not need JWT
  if (
      req.url.includes('/api/auth/login') ||
      req.url.includes('/api/auth/register')
  ) {
    return next(req);
  }

  // Add JWT to every other backend request
  if (token) {

    const authReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });

    console.log(
        'JWT attached to request:',
        req.url
    );

    return next(authReq);
  }

  console.warn(
      'No JWT token found for:',
      req.url
  );

  return next(req);
};