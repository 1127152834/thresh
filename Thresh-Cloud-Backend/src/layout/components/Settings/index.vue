<template>
  <el-drawer
    title="系统布局配置"
    :visible.sync="flag"
    :close-on-press-escape="false"
    :wrapper-closable="true"
    :size="300"
    :before-close="handleClose"
    direction="rtl"
  >
    <div class="drawer-container">
      <div>
        <div class="drawer-item">
          <span>{{ $t('settings.theme') }}</span>
          <theme-picker style="float: right;height: 26px;margin: -3px 8px 0 0;" @change="themeChange" />
        </div>
        <div class="drawer-item">
          <span>侧栏风格</span>
          <el-switch
            v-model="sidebarColor"
            class="drawer-switch"
            active-value="dark"
            inactive-value="white"
            active-text="黑色"
            inactive-text="白色"
          />
        </div>

        <div class="drawer-item">
          <span>{{ $t('settings.tagsView') }}</span>
          <el-switch
            v-model="tagsView"
            class="drawer-switch"
            active-text="开启"
            inactive-text="关闭"
          />
        </div>

        <div class="drawer-item">
          <span>{{ $t('settings.fixedHeader') }}</span>
          <el-switch
            v-model="fixedHeader"
            class="drawer-switch"
            active-text="固定"
            inactive-text="随动"
          />
        </div>

        <div class="drawer-item">
          <span>{{ $t('settings.sidebarLogo') }}</span>
          <el-switch
            v-model="sidebarLogo"
            class="drawer-switch"
            active-text="开启"
            inactive-text="关闭"
          />
        </div>
      </div>
    </div>
  </el-drawer>
</template>

<script>
import ThemePicker from '@/components/ThemePicker'

export default {
  components: { ThemePicker },
  data() {
    return {
      flag: this.$store.state.setting.settingBar.opened
    }
  },
  computed: {
    sidebarColor: {
      get() {
        return this.$store.state.setting.sideBarTheme
      },
      set(val) {
        this.$store.commit('setting/setSideBarTheme', val)
        this.$put('system/user/theme', { theme: val })
      }
    },
    fixedHeader: {
      get() {
        return this.$store.state.setting.fixHeader
      },
      set(val) {
        this.$store.commit('setting/fixHeader', val)
      }
    },
    tagsView: {
      get() {
        return this.$store.state.setting.multipage
      },
      set(val) {
        this.$store.commit('setting/setMultipage', val)
      }
    },
    sidebarLogo: {
      get() {
        return this.$store.state.setting.sidebarLogo
      },
      set(val) {
        this.$store.commit('setting/setSidebarLogo', val)
      }
    }
  },
  methods: {
    themeChange(val) {
      this.$store.commit('setting/setTheme', val)
    },
    handleClose() {
      this.$store.commit('setting/openSettingBar', false)
    }
  }
}
</script>

<style lang="scss" scoped>
.drawer-container {
  padding: 24px;
  font-size: 14px;
  line-height: 1.5;
  word-wrap: break-word;

  .drawer-title {
    margin-bottom: 12px;
    color: rgba(0, 0, 0, .85);
    font-size: 14px;
    line-height: 22px;
  }

  .drawer-item {
    color: rgba(0, 0, 0, .65);
    font-size: 14px;
    padding: 12px 0;
  }

  .drawer-switch {
    float: right
  }
}
</style>
