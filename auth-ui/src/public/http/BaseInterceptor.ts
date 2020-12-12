import { Injectable } from '@angular/core';
import {
  HttpEvent,
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
  HttpClient,
  HttpResponse,
  HttpHeaders,
  HttpErrorResponse,
} from '@angular/common/http';
import { merge, Observable, of } from 'rxjs';
import { map, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { LocalCatch } from '../utils/localCatch';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';

@Injectable({
  providedIn: 'root',
})
export class BaseInterceptor implements HttpInterceptor {
  constructor(private router: Router, private message: NzMessageService) {}

  /** 补全地址 */
  matchUrl(url: string): string {
    if (!url) throw new Error('URL is empty!!!');
    if (url.startsWith('http://') || url.startsWith('https://')) {
      return url;
    }
    return environment.api + url;
  }

  /** 请求/响应拦截 */
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const token = LocalCatch.getToken();
    if (token) {
      req = req.clone({
        url: this.matchUrl(req.url),
        headers: req.headers.set('Authorization', token),
      });
    } else {
      req = req.clone({
        url: this.matchUrl(req.url),
      });
    }

    // 打印请求
    // console.log(req);
    

    return next.handle(req).pipe(
      tap(
        (event: HttpEvent<any>) => {
            // 打印响应
            // console.log(event);
        },
        (error: HttpResponse<any>) => {
          switch (error.status) {
            case 401:
              this.message.error('身份过期！');
              this.router.navigateByUrl('login');
              break;
            case 403:
              this.message.error('您无此权限！');
              break;
            case 404:
              this.message.error('请求资源不存在！');
              break;
            case 408:
              this.message.error('请求超时！');
              break;
            default:
              this.message.error('服务异常！');
          }
        }
      ),
      map((result: HttpEvent<any>) => {
        if (result instanceof HttpResponse && result.body) {
            if (result.body.msg && typeof result.body.msg === 'string') {
              this.message.create(result.body.ok ? 'success' : 'error', result.body.msg);
            }
        }
        return result;
      })
    );
  }
}
