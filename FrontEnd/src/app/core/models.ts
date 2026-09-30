export interface User {
  id: number;
  name: string;
  email: string;
  role: 'ADMIN' | 'STAFF' | string;
}

export interface LoginResponse extends User {
  token: string;
}

export interface Category {
  id?: number;
  name: string;
  description: string;
}

export interface Product {
  id?: number;
  name: string;
  sku: string;
  price: number;
  quantity: number;
  reorderLevel: number;
  category: { id: number } | null;
}

export interface StockTransaction {
  id?: number;
  product: Product;
  type: 'STOCK_IN' | 'STOCK_OUT';
  quantity: number;
  description?: string;
  transactionDate: string;
}

export interface DashboardSummary {
  totalProducts: number;
  totalCategories: number;
  totalStock: number;
  lowStockProducts: number;
}