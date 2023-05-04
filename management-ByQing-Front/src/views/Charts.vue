<template>
	<h1>Echarts图表</h1>
  <div id="myChart" style="width:1500px;height:500px;margin:auto;"></div>
</template>

<script>
// 引入echarts
import * as echarts from 'echarts'
import {onMounted} from "vue";
import request from '../utils/request';

export default {
    
        
    
    data() {
        return {
            ppower:[],
            shijian:[],
        }
    },
    mounted(){
        request.get('/charts').then(res =>{
            console.log(res)
            this.ppower = res[0].power
            this.time = res[0].shijian

            console.log(this.ppower)
            setTimeout(()=>{ 
                let myChart = echarts.init(document.getElementById("myChart"));
// 设置图表配置项,可以直接从Echarts的示例中，将配置项复制下来后放入下列的myChart.setOption()中实现图表的样式更换

      myChart.setOption({
        title: {
          text: 'Stacked Area Chart'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            label: {
              backgroundColor: '#6a7985'
            }
          }
        },
        legend: {
          data: ['逆变器1','逆变器2','逆变器3','逆变器4','逆变器5','逆变器6','逆变器7','逆变器8','逆变器9','逆变器10']
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            boundaryGap: false,
            data: this.shijian,
            axisLabel: {
                        interval: 0,
                        rotate: 60
                    }
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [
          {
            name: '逆变器1',
            type: 'line',
            stack: 'Total',
            areaStyle: {},
            emphasis: {
              focus: 'series'
            },
            data: this.ppower
          },
          {
            name: '逆变器2',
            type: 'line',
            stack: 'Total',
            areaStyle: {},
            emphasis: {
              focus: 'series'
            },
            data: res[1].power
          },
          {
            name: '逆变器3',
            type: 'line',
            stack: 'Total',
            areaStyle: {},
            emphasis: {
              focus: 'series'
            },
            data: res[2].power
          },
          {
            name: '逆变器4',
            type: 'line',
            stack: 'Total',
            areaStyle: {},
            emphasis: {
              focus: 'series'
            },
            data: res[3].power
          },
          {
            name: '逆变器5',
            type: 'line',
            stack: 'Total',
            areaStyle: {},
            emphasis: {
              focus: 'series'
            },
            data: res[4].power
          },
          {
            name: '逆变器6',
            type: 'line',
            stack: 'Total',
            areaStyle: {},
            emphasis: {
              focus: 'series'
            },
            data: res[5].power
          },
          {
            name: '逆变器7',
            type: 'line',
            stack: 'Total',
            areaStyle: {},
            emphasis: {
              focus: 'series'
            },
            data: res[6].power
          },
          {
            name: '逆变器8',
            type: 'line',
            stack: 'Total',
            areaStyle: {},
            emphasis: {
              focus: 'series'
            },
            data: res[7].power
          },
          {
            name: '逆变器9',
            type: 'line',
            stack: 'Total',
            areaStyle: {},
            emphasis: {
              focus: 'series'
            },
            data: res[8].power
          },
          {
            name: '逆变器10',
            type: 'line',
            stack: 'Total',
            areaStyle: {},
            emphasis: {
              focus: 'series'
            },
            data: res[9].power
          },

        //   {
        //     name: 'Union Ads',
        //     type: 'line',
        //     stack: 'Total',
        //     areaStyle: {},
        //     emphasis: {
        //       focus: 'series'
        //     },
        //     data: [220, 182, 191, 234, 290, 330, 310]
        //   },
        //   {
        //     name: 'Video Ads',
        //     type: 'line',
        //     stack: 'Total',
        //     areaStyle: {},
        //     emphasis: {
        //       focus: 'series'
        //     },
        //     data: [150, 232, 201, 154, 190, 330, 410]
        //   },
        //   {
        //     name: 'Direct',
        //     type: 'line',
        //     stack: 'Total',
        //     areaStyle: {},
        //     emphasis: {
        //       focus: 'series'
        //     },
        //     data: [320, 332, 301, 334, 390, 330, 320]
        //   },
        //   {
        //     name: 'Search Engine',
        //     type: 'line',
        //     stack: 'Total',
        //     label: {
        //       show: true,
        //       position: 'top'
        //     },
        //     areaStyle: {},
        //     emphasis: {
        //       focus: 'series'
        //     },
        //     data: [820, 932, 901, 934, 1290, 1330, 1320]
        //   }
        ]
            });
            window.onresize = function () { // 自适应大小
              myChart.resize();
            };
             }, 5000);
            
        })// 需要在页面加载完毕后展示图表，这里使用Vue3的组合式生命周期钩子 onMounted()
    },
}
</script>
