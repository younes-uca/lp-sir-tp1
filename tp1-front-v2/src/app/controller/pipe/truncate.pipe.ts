import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'truncate'
})
export class TruncatePipe implements PipeTransform {


  transform(text: any, length: number = 20, suffix: string = '...'): string {
    if (typeof text === 'string') {
      if (text.length > length) {
        const truncated: string = text.substring(0, length).trim() + suffix;
        return truncated;
      }
    }
    return text;

  }


}
