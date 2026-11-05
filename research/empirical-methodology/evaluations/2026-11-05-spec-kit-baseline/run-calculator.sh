#!/bin/sh
set -eu
run_dir=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
study_root=$(CDPATH= cd -- "$run_dir/../../../.." && pwd)
baseline="$study_root/tmp/spec-kit-evaluation/baseline"
expected=88f56d0979e2fae1fb5d59e8681975e3a1fc2a27
actual=$(git -C "$baseline" rev-parse HEAD)
if [ "$actual" != "$expected" ]; then
  echo "Unexpected snapshot: $actual" >&2
  exit 1
fi
export JAVA_HOME=/Library/Java/JavaVirtualMachines/amazon-corretto-22.jdk/Contents/Home
cd "$baseline/github-spec-kit-calculator"
exec "$JAVA_HOME/bin/java" -jar target/baseline-calculator-1.0.0.jar
