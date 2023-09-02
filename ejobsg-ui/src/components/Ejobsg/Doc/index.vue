<template>
  <div>
    <svg-icon icon-class="question" @click="goto" />
  </div>
</template>

<script>
export default {
  name: 'EjobsgDoc',
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
