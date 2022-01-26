<template>
  <div class="block" style="padding-top: 10px">
    <template v-if="finished">
      <el-timeline>
        <el-timeline-item v-for="(item,index) of timeline" :key="index" :timestamp="item.loginTime" placement="top">
          <el-card>
            <p><el-icon class="el-icon-link" />  {{ $t('table.loginLog.ip') }}：{{ item.ip }} </p>
            <p><el-icon class="el-icon-bangzhu" />  {{ $t('table.loginLog.browser') }}：{{ item.browser }} </p>
            <p><el-icon class="el-icon-monitor" />  {{ $t('table.loginLog.system') }}：{{ item.system }} </p>
          </el-card>
        </el-timeline-item>
      </el-timeline>
    </template>
    <template v-else>
      <el-skeleton :rows="20" animated />
    </template>
  </div>
</template>

<script>
export default {
  data() {
    return {
      timeline: [],
      finished: false
    }
  },
  mounted() {
    this.getTimeLine()
  },
  methods: {
    getTimeLine() {
      this.finished = false
      this.$get('system/loginLog/currentUser').then((r) => {
        this.timeline = r.data.data
        this.finished = true
      })
    }
  }
}
</script>
<style lang="scss" scoped>
.el-card.is-always-shadow {
  box-shadow: none;
}
.el-card {
  border: 1px solid #f1f1f1;
  border-radius: 2px;
}
</style>
