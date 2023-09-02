<template>
  <div>
    <svg-icon icon-class="github" @click="goto" />
  </div>
</template>

<script>
export default {
  name: 'EjobsgGit',
  data() {
    return {
      url: 'https://gitee.com/voishion/ai-ejobsg'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>
