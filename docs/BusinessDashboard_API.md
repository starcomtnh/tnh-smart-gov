# Business Dashboard API Documentation

## Tổng quan

Business Dashboard API cung cấp các endpoint để quản lý và thống kê dữ liệu dashboard kinh doanh của hệ thống camera trường học.

## Các tính năng chính

### 1. Quản lý cơ bản (CRUD)

- **POST** `/api/v1/business-dashboard` - Tạo mới dashboard
- **PUT** `/api/v1/business-dashboard/{id}` - Cập nhật dashboard
- **GET** `/api/v1/business-dashboard/{id}` - Lấy thông tin dashboard theo ID
- **DELETE** `/api/v1/business-dashboard/{id}` - Xóa dashboard
- **GET** `/api/v1/business-dashboard` - Lấy danh sách tất cả dashboard

### 2. Tìm kiếm và lọc dữ liệu

- **GET** `/api/v1/business-dashboard/by-zone?zoneName={zoneName}` - Tìm kiếm theo tên zone
- **GET** `/api/v1/business-dashboard/by-year/{year}` - Tìm kiếm theo năm đăng ký
- **GET** `/api/v1/business-dashboard/statistics/{year}` - Lấy thống kê theo năm

### 3. Thống kê tổng hợp

- **GET** `/api/v1/business-dashboard/total-amount?zoneName={zoneName}` - Tổng số tiền theo zone
- **GET** `/api/v1/business-dashboard/total-students?zoneName={zoneName}` - Tổng số học sinh theo zone
- **GET** `/api/v1/business-dashboard/total-cameras?zoneName={zoneName}` - Tổng số camera theo zone

### 4. Báo cáo và ranking

- **GET** `/api/v1/business-dashboard/top-zones-students?limit={limit}` - Top zones có nhiều học sinh nhất
- **GET** `/api/v1/business-dashboard/zones-payment-rate?limit={limit}` - Zones theo tỷ lệ thanh toán
- **GET** `/api/v1/business-dashboard/reports/yearly/{year}` - Báo cáo tổng hợp theo năm
- **GET** `/api/v1/business-dashboard/reports/zone?zoneName={zoneName}` - Báo cáo tổng hợp theo zone

## Cấu trúc dữ liệu

### BusinessDashboardDTO

```json
{
  "id": "uuid",
  "zoneName": "string",
  "cameras": "integer",
  "students": "integer",
  "registrationYear": "integer",
  "registers": "integer",
  "notPaid": "integer",
  "paid": "integer",
  "totalAmount": "double"
}
```

### BusinessDashboardFormDTO

```json
{
  "zoneName": "string (required, max 255 chars)",
  "cameras": "integer (min 0)",
  "students": "integer (min 0)",
  "registrationYear": "integer (required, 2000-2100)",
  "registers": "integer (min 0)",
  "notPaid": "integer (min 0)",
  "paid": "integer (min 0)",
  "totalAmount": "double (min 0.0)"
}
```

## Ví dụ sử dụng

### Tạo mới dashboard

```bash
POST /api/v1/business-dashboard
Content-Type: application/json

{
  "zoneName": "Khu vực Hà Nội",
  "cameras": 25,
  "students": 500,
  "registrationYear": 2025,
  "registers": 450,
  "notPaid": 80,
  "paid": 370,
  "totalAmount": 75000000.00
}
```

### Lấy top 5 zones có nhiều học sinh nhất

```bash
GET /api/v1/business-dashboard/top-zones-students?limit=5
```

### Tạo báo cáo năm 2025

```bash
GET /api/v1/business-dashboard/reports/yearly/2025
```

## Response Format

Tất cả API đều trả về format:

```json
{
  "data": "...",
  "message": "success.operation.completed",
  "timestamp": "2025-01-21T10:30:00Z"
}
```

## Xử lý lỗi

- **400 Bad Request**: Dữ liệu đầu vào không hợp lệ
- **404 Not Found**: Không tìm thấy resource
- **500 Internal Server Error**: Lỗi server

## Cấu hình

Có thể cấu hình trong file `application-business-dashboard.yml`:

```yaml
app:
  business-dashboard:
    default-top-zones-limit: 10
    default-report-year: 2025
    auto-report-generation: true
    report-cache-time: 3600
```
