/*
 * Copyright (c) 2014-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package com.taobao.weex.devtools.common.android;

import android.app.Dialog;

public interface DialogFragmentAccessor<DIALOG_FRAGMENT, FRAGMENT, FRAGMENT_MANAGER>
    extends FragmentAccessor<FRAGMENT, FRAGMENT_MANAGER> {
  Dialog getDialog(DIALOG_FRAGMENT dialogFragment);
}
