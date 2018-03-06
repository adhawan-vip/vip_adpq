import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { ProdConfig } from './blocks/config/prod.config';
import { VipAdpqAppModule } from './app.module';
import 'font-awesome/css/font-awesome.min.css';
import 'primeng/resources/themes/omega/theme.css';
import 'primeng/resources/primeng.min.css';

ProdConfig();

if (module['hot']) {
    module['hot'].accept();
}

platformBrowserDynamic().bootstrapModule(VipAdpqAppModule)
.then((success) => console.log(`Application started`))
.catch((err) => console.error(err));
